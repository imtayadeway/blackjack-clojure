(ns blackjack-clojure.core)

(def suits #{"♠" "♥" "♦" "♣"})
(def ranks (set (concat ["ace"] (range 2 11) ["jack" "queen" "king"])))

(def cards-in-unicode
  {"ace"   {"♠" "🂡", "♥" "🂱", "♦" "🃁", "♣" "🃑"}
   2       {"♠" "🂢", "♥" "🂲", "♦" "🃂", "♣" "🃒"}
   3       {"♠" "🂣", "♥" "🂳", "♦" "🃃", "♣" "🃓"}
   4       {"♠" "🂤", "♥" "🂴", "♦" "🃄", "♣" "🃔"}
   5       {"♠" "🂥", "♥" "🂵", "♦" "🃅", "♣" "🃕"}
   6       {"♠" "🂦", "♥" "🂶", "♦" "🃆", "♣" "🃖"}
   7       {"♠" "🂧", "♥" "🂷", "♦" "🃇", "♣" "🃗"}
   8       {"♠" "🂨", "♥" "🂸", "♦" "🃈", "♣" "🃘"}
   9       {"♠" "🂩", "♥" "🂹", "♦" "🃉", "♣" "🃙"}
   10      {"♠" "🂪", "♥" "🂺", "♦" "🃊", "♣" "🃚"}
   "jack"  {"♠" "🂫", "♥" "🂻", "♦" "🃋", "♣" "🃛"}
   "queen" {"♠" "🂭", "♥" "🂽", "♦" "🃍", "♣" "🃝"}
   "king"  {"♠" "🂮", "♥" "🂾", "♦" "🃎", "♣" "🃞"}})

(def card-back-in-unicode
  "🂠")

(def deck
  (ref (into [] (for [suit suits
                      rank ranks]
                  {:suit suit, :rank rank}))))

(def dealer-hand (ref []))
(def player-hand (ref []))

(defn deal-card
  [hand deck]
  (dosync
   (ref-set hand (conj @hand (first @deck)))
   (ref-set deck (rest @deck))))

(defn deal
  [hand deck]
  (dotimes [n 2] (deal-card hand deck)))

(defn return-cards
  [hand deck]
  (dosync
   (ref-set deck (vec (concat @deck @hand)))
   (ref-set hand [])))

(defn shuffle-deck
  [deck]
  (dosync (alter deck shuffle)))

(defn card-to-unicode
  [card]
  (let [{rank :rank suit :suit} card]
    ((cards-in-unicode rank) suit)))

(defn draw-cards
  [cards]
  (apply str (interpose " " cards)))

(defn draw-obscured-hand
  [hand]
  (draw-cards (cons (card-to-unicode (first hand))
                    (repeat (count (rest hand)) card-back-in-unicode))))

(defn draw-hand
  [hand]
  (draw-cards (map card-to-unicode hand)))

(defn play-round
  []
  (do
    (shuffle-deck deck)
    (deal player-hand deck)
    (deal dealer-hand deck)
    (println "Dealer:")
    (println (draw-obscured-hand (deref dealer-hand)))
    (println "Player:")
    (println (draw-hand (deref player-hand)))
    (return-cards player-hand deck)
    (return-cards dealer-hand deck)))

(defn card-value
  [card accumulative-score]
  (let [rank (:rank card)]
    (case rank
      (2 3 4 5 6 7 8 9 10) rank
      ("jack" "queen" "king") 10
      "ace" (if (> accumulative-score 10) 1 11))))

(defn high-value
  [card]
  (card-value card 0))

(defn recursive-score
  [hand accumulative-score]
  (if (empty? hand)
    accumulative-score
    (recur (rest hand)
           (+ accumulative-score
              (card-value (first hand) accumulative-score)))))

(defn score-hand
  [hand]
  (let [sorted-hand (sort-by high-value hand)]
    (recursive-score sorted-hand 0)))

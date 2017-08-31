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
  (draw-cards [(card-to-unicode (first hand))
               card-back-in-unicode]))

(defn draw-hand
  [hand]
  (draw-cards (map card-to-unicode hand)))

(defn bust?
  [hand]
  true)

(defn blackjack?
  [hand]
  true)

(defn play-hand
  []
  (do
    (println "Hit [h] or stand [s]?")
    (let [input (read-line)]
      (cond (= "h" input)
            (do
              (deal-card player-hand deck)
              (cond (bust? player-hand) "do something"
                    (blackjack? player-hand) "you won!"
                    :else (play-hand)))))
    (println (draw-hand (deref player-hand)))))

(defn draw-game
  []
  (do
    (println "Dealer:")
    (println (draw-obscured-hand (deref dealer-hand)))
    (println "Player:")
    (println (draw-hand (deref player-hand)))))

(defn play-round
  []
  (do
    (shuffle-deck deck)
    (deal player-hand deck)
    (deal dealer-hand deck)
    (draw-game)
    (play-hand)
    (println "This is your score:" (score-hand player-hand))
    (return-cards player-hand deck)
    (return-cards dealer-hand deck)))

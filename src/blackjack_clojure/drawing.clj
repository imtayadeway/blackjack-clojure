(ns blackjack-clojure.drawing)

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

(defn card-to-unicode
  [card]
  (let [{rank :rank suit :suit} card]
    ((cards-in-unicode rank) suit)))

(defn draw-cards
  [cards]
  (clojure.string/join " " cards))

(defn draw-obscured-hand
  [hand]
  (draw-cards [(card-to-unicode (first hand))
               card-back-in-unicode]))

(defn draw-hand
  [hand]
  (draw-cards (map card-to-unicode hand)))

(defn draw-game
  [player-hand dealer-hand]
  (->> ["Dealer:" (draw-obscured-hand dealer-hand)
        "Player:" (draw-hand player-hand)]
       (interpose "\n")
       (apply str)))

(defn draw-unobscured-game
  [player-hand dealer-hand]
  (->> ["Dealer:" (draw-hand dealer-hand)
        "Player:" (draw-hand player-hand)]
       (interpose "\n")
       (apply str)))

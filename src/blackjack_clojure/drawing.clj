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
  (apply str (interpose " " cards)))

(defn draw-obscured-hand
  [hand]
  (draw-cards [(card-to-unicode (first hand))
               card-back-in-unicode]))

(defn draw-hand
  [hand]
  (draw-cards (map card-to-unicode hand)))

(defn clear-screen
  []
  (print (str (char 27) "[2J")))

(defn draw-game
  [player-hand dealer-hand]
  (do
    (clear-screen)
    (println "Dealer:")
    (println (draw-obscured-hand dealer-hand))
    (println "Player:")
    (println (draw-hand player-hand))))

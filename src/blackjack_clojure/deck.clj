(ns blackjack-clojure.deck)

(def suits #{"♠" "♥" "♦" "♣"})
(def ranks (set (concat ["ace"] (range 2 11) ["jack" "queen" "king"])))
(def deck (vec (for [suit suits
                     rank ranks]
                 {:suit suit, :rank rank})))

(defn create-deck
  [n]
  (->> (repeat n deck)
       (reduce concat)
       (shuffle)))

(defn draw
  [deck hand]
  [(rest deck) (conj hand (first deck))])

(defn deal
  [deck]
  (apply draw (draw deck [])))

(defn return-cards
  [deck player-hand dealer-hand]
  (vec (concat deck player-hand dealer-hand)))

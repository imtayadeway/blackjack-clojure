(ns blackjack-clojure.deck)

(def suits #{"♠" "♥" "♦" "♣"})
(def ranks (set (concat (range 2 11) ["jack" "queen" "king" "ace"])))
(def deck (vec (for [suit suits
                     rank ranks]
                 {:suit suit, :rank rank})))

(defn create-deck
  [n]
  (->> (repeat n deck)
       (reduce concat)
       shuffle
       (into clojure.lang.PersistentQueue/EMPTY)))

(defn draw
  [deck hand]
  [(pop deck) (conj hand (peek deck))])

(defn deal
  [deck]
  (apply draw (draw deck [])))

(defn return-cards
  [deck cards]
  (into deck cards))

(ns blackjack-clojure.deck)

(def suits #{"♠" "♥" "♦" "♣"})
(def ranks (set (concat ["ace"] (range 2 11) ["jack" "queen" "king"])))
(def deck (into [] (for [suit suits
                         rank ranks]
                     {:suit suit, :rank rank})))

(defn create-deck
  [n]
  (->> (repeat n deck)
       (reduce concat)
       (shuffle)))

(ns blackjack-clojure.core)

(def suits #{"♠" "♥" "♦" "♣"})
(def ranks (concat (range 1 11) ["jack" "queen" "king"]))
(def deck
  (into [] (for [s suits
                r ranks]
            {:rank r, :suit s})))

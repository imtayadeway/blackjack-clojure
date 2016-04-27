(ns blackjack-clojure.core)

(def suits #{"♠" "♥" "♦" "♣"})
(def ranks (set (concat ["ace"] (range 2 11) ["jack" "queen" "king"])))
(def deck
  (into [] (for [suit suits
                 rank ranks]
             {:suit suit, :rank rank})))

(defn card-value
  [card]
  (let [rank (:rank card)]
    (case rank
      (2 3 4 5 6 7 8 9 10) rank
      ("jack" "queen" "king") 10
      "ace" 11)))

(defn any-aces? [values] (some #(= 11 %) values))

(defn score-values
  [values]
  (let [sorted-values (-> values (sort) (vec))
        sum (reduce + values)]
    (if (<= sum 21)
      sum
      (if (any-aces? values)
        (recur (-> sorted-values (pop) (conj 1)))
        sum))))

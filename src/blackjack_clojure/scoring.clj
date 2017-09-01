(ns blackjack-clojure.scoring)

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
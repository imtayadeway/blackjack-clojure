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
  (if (seq hand)
    (recur (next hand)
           (+ accumulative-score
              (card-value (first hand) accumulative-score)))
    accumulative-score))

(defn score-hand
  [hand]
  (let [sorted-hand (sort-by high-value hand)]
    (recursive-score sorted-hand 0)))

(defn bust?
  [hand]
  (< 21 (score-hand hand)))

(defn won?
  [player-hand dealer-hand]
  (let [player-score (score-hand player-hand)
        dealer-score (score-hand dealer-hand)]
    (and (not (bust? player-hand))
         (or (> player-score dealer-score)
             (bust? dealer-hand)))))

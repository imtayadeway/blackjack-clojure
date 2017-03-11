(ns blackjack-clojure.core)

(def suits #{"♠" "♥" "♦" "♣"})
(def ranks (set (concat ["ace"] (range 2 11) ["jack" "queen" "king"])))
(def deck
  (ref (into [] (for [suit suits
                      rank ranks]
                  {:suit suit, :rank rank}))))

(def dealer-hand (ref []))
(def player-hand (ref []))

(defn deal
  [hand deck]
  (dosync
   (alter hand concat @hand (take 2 @deck))
   (ref-set deck (vec (drop 2 @deck)))))

(defn shuffle-deck
  [deck]
  (dosync (alter deck shuffle)))

(defn play-round
  []
  (shuffle-deck deck)
  (deal player-hand deck)
  (deal dealer-hand deck))

(defn card-value
  [card accumulative-score]
  (let [rank (:rank card)]
    (case rank
      (2 3 4 5 6 7 8 9 10) rank
      ("jack" "queen" "king") 10
      "ace" (if (> accumulative-score 10) 1 11))))

(defn high-value
  [card]
  (let [rank (:rank card)]
    (case rank
      (2 3 4 5 6 7 8 9 10) rank
      ("jack" "queen" "king") 10
      "ace" 11)))

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

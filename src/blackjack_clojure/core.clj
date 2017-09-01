(ns blackjack-clojure.core)
(require '[blackjack-clojure.scoring :as scoring])
(require '[blackjack-clojure.drawing :as drawing])
(require '[blackjack-clojure.deck :as deck])

;; (defn dealer-play
;;   [deck hand]
;;   (let [score (scoring/score-hand hand)]
;;     (cond (< score 18)
;;           (recur (rest deck (conj hand (first deck))))
;;           :else '(deck hand))))

(defn deal
  [deck]
  [(subvec deck 0 2) (subvec deck 2 4) (drop 4 deck)])

(defn player-turn
  [hand deck]
  [hand deck])

(defn dealer-turn
  [hand deck]
  [hand deck])

(defn return-cards
  [deck player-hand dealer-hand]
  (vec (concat deck player-hand dealer-hand)))

(defn play-round
  [deck]
  (let [[player-initial-hand dealer-initial-hand deck-after-deal] (deal deck)
        [player-final-hand deck-after-player-turn] (player-turn player-initial-hand deck-after-deal)
        [dealer-final-hand deck-after-dealer-turn] (dealer-turn dealer-initial-hand deck-after-player-turn)]

    (return-cards deck-after-dealer-turn player-final-hand dealer-final-hand)))

(defn -main
  [& args]
  (loop [deck (deck/create-deck 4)]
    (recur (play-round deck))))

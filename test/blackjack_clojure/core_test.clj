(ns blackjack-clojure.core-test
  (:require [clojure.test :refer :all]
            [blackjack-clojure.core :refer :all]))

(deftest all-the-ranks
  (testing "That we have the full set of ranks in a standard deck."
    (is (= #{"ace" 2 3 4 5 6 7 8 9 10 "jack" "queen" "king"} ranks))))

(deftest all-the-suits
  (testing "That we have all the suits in a standard deck."
    (is (= #{"♠" "♥" "♦" "♣"} suits))))

(deftest deck-count
  (testing "That we have 52 cards in the deck."
    (is (= (count deck) 52))))

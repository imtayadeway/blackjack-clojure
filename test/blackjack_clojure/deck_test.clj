(ns blackjack-clojure.deck-test
  (:require [clojure.test :refer :all]
            [blackjack-clojure.deck :refer :all]))

(deftest all-the-ranks
  (testing "That we have the full set of ranks in a standard deck."
    (is (= #{"ace" 2 3 4 5 6 7 8 9 10 "jack" "queen" "king"} ranks))))

(deftest all-the-suits
  (testing "That we have all the suits in a standard deck."
    (is (= #{"♠" "♥" "♦" "♣"} suits))))

(deftest single-deck-count
  (testing "That we have 52 cards in a single deck."
    (is (= (count (create-deck 1)) 52))))

(deftest two-deck-count
  (testing "That we have 104 cards with two decks."
    (is (= (count (create-deck 2)) 104))))

(deftest four-deck-count
  (testing "That we have 208 cards with four decks."
    (is (= (count (create-deck 4)) 208))))

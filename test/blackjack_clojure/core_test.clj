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

(deftest numbered-card-value
  (testing "That a numbered card returns its value"
    (is (= 2 (card-value {:rank 2, :suit "♠"})))))

(deftest face-card-value
  (testing "That a face card returns a 10"
    (is (= 10 (card-value {:rank "jack", :suit "♠"})))))

(deftest ace-value
  (testing "That aces are high"
    (is (= 11 (card-value {:rank "ace", :suit "♠"})))))

(deftest score-two-twos
  (testing "That we can score a hand with two twos."
    (is (= 4 (score-values [2 2])))))

(deftest score-two-three
  (testing "That we can score a hand with a two and a three."
    (is (= 5 (score-values [2 3])))))

(deftest score-two-three-four
  (testing "That we can score a hand with three cards."
    (is (= 9 (score-values [2 3 4])))))

(deftest score-with-face-card
  (testing "That we can score a hand with a face card in it."
    (is (= 12 (score-values [10 2])))))

(deftest score-ace-two
  (testing "That we can score a hand with a high ace."
    (is (= 13 (score-values [11 2])))))

(deftest score-ace-11
  (testing "That we can score a hand with a low ace.")
  (is (= 12 (score-values [11 9 2]))))

(deftest score-11-ace
  (testing "That we can score a hand with a low ace irrespective of order."
    (is (= 12 (score-values [9 2 11])))))

(deftest score-ace-ace
  (testing "That we can score a hand with one high and one low ace."
    (is (= 12 (score-values [11 11])))))

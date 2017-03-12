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
    (is (= (count (deref deck)) 52))))

(deftest score-two-twos
  (testing "That we can score a hand with two twos."
    (is (= 4 (score-hand [{:rank 2, :suit "♠"},
                          {:rank 2, :suit "♥"}])))))

(deftest score-two-three
  (testing "That we can score a hand with a two and a three."
    (is (= 5 (score-hand [{:rank 2, :suit "♠"},
                          {:rank 3, :suit "♠"}])))))

(deftest score-two-three-four
  (testing "That we can score a hand with three cards."
    (is (= 9 (score-hand [{:rank 2, :suit "♠"},
                          {:rank 3, :suit "♠"},
                          {:rank 4, :suit "♠"}])))))

(deftest score-with-face-card
  (testing "That we can score a hand with a face card in it."
    (is (= 12 (score-hand [{:rank 10, :suit "♠"},
                           {:rank 2, :suit "♠"}])))))

(deftest score-ace-two
  (testing "That we can score a hand with a high ace."
    (is (= 13 (score-hand [{:rank "ace", :suit "♠"},
                           {:rank 2, :suit "♠"}])))))

(deftest score-ace-11
  (testing "That we can score a hand with a low ace.")
  (is (= 12 (score-hand [{:rank "ace", :suit "♠"},
                         {:rank 9, :suit "♠"},
                         {:rank 2, :suit "♠"}]))))

(deftest score-11-ace
  (testing "That we can score a hand with a low ace irrespective of order."
    (is (= 12 (score-hand [{:rank 9, :suit "♠"},
                           {:rank 2, :suit "♠"},
                           {:rank "ace", :suit "♠"}])))))

(deftest score-ace-ace
  (testing "That we can score a hand with one high and one low ace."
    (is (= 12 (score-hand [{:rank "ace", :suit "♠"},
                           {:rank "ace", :suit "♥"}])))))

(deftest draw-player-hand
  (testing "That a hand can be represented as a string."
    (is (= "🂡🂱"
           (draw-hand [{:rank "ace", :suit "♠"},
                       {:rank "ace", :suit "♥"}])))))

(deftest draw-dealers-hand
  (testing "That a dealer's hand can be represented as a string."
    (is (= "🂡🂠"
           (draw-obscured-hand [{:rank "ace", :suit "♠"},
                                {:rank "ace", :suit "♥"}])))))

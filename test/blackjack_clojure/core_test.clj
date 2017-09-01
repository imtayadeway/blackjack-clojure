(ns blackjack-clojure.core-test
  (:require [clojure.test :refer :all]
            [blackjack-clojure.core :refer :all]))

(deftest dealing
  (testing "That two cards are dealt each from the deck"
    (let [deck [{:rank "ace", :suit "♠"}
                {:rank 2, :suit "♠"}
                {:rank 3, :suit "♠"}
                {:rank 4, :suit "♠"}
                {:rank 5, :suit "♠"}
                {:rank 6, :suit "♠"}]
          expected [[{:rank "ace", :suit "♠"} {:rank 2, :suit "♠"}]
                    [{:rank 3, :suit "♠"}, {:rank 4, :suit "♠"}]
                    [{:rank 5, :suit "♠"} {:rank 6, :suit "♠"}]]
          actual (deal deck)]
      (is (= expected actual)))))

(deftest returning
  (testing "That the cards are returned to the pack in the correct order"
    (let [player-hand [{:rank "ace", :suit "♠"} {:rank 2, :suit "♠"}]
          dealer-hand [{:rank 3, :suit "♠"}, {:rank 4, :suit "♠"}]
          deck [{:rank 5, :suit "♠"} {:rank 6, :suit "♠"}]
          expected [{:rank 5, :suit "♠"}
                    {:rank 6, :suit "♠"}
                    {:rank "ace", :suit "♠"}
                    {:rank 2, :suit "♠"}
                    {:rank 3, :suit "♠"}
                    {:rank 4, :suit "♠"}]
          actual (return-cards deck player-hand dealer-hand)]
      (is (= expected actual)))))

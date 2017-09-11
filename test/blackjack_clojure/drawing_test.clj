(ns blackjack-clojure.drawing-test
  (:require [clojure.test :refer :all]
            [blackjack-clojure.drawing :refer :all]))

(deftest draw-player-hand
  (testing "That a hand can be represented as a string."
    (is (= "🂡 🂱"
           (draw-hand [{:rank "ace", :suit "♠"},
                       {:rank "ace", :suit "♥"}])))))

(deftest draw-dealers-hand
  (testing "That a dealer's hand can be represented as a string."
    (is (= "🂡 🂠"
           (draw-obscured-hand [{:rank "ace", :suit "♠"},
                                {:rank "ace", :suit "♥"}])))))

(deftest draw-game-test
  (let [actual (draw-game
                [{:rank "ace", :suit "♠"}
                 {:rank 2, :suit "♠"}]
                [{:rank 3, :suit "♠"}
                 {:rank 4, :suit "♠"}])
        expected "Dealer:\n🂣 🂠\nPlayer:\n🂡 🂢"]
    (is (= expected actual))))

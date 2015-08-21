(ns re-frame-examples.core-test
  (:require
   [cljs.test :refer-macros [deftest testing is]]
   [re-frame-examples.core :as core]))

(deftest fake-test
  (testing "fake description"
    (is (= 1 2))))

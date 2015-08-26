(ns re-frame-examples.phonecat.state)

(def initial-phonecat-state
  {:phones []
   :phone-details {}
   :search-input ""
   :order-prop "name"})

(defn merge-phonecat-state
  [state _]
  (into state initial-phonecat-state))


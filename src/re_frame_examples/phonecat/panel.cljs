(ns re-frame-examples.phonecat.panel
    (:require [reagent.core :as r]
              [re-frame.core :as rf]
              [re-frame-examples.phonecat.subs]
              [re-frame-examples.phonecat.handlers]
              [re-frame-examples.phonecat.views.phone-list :as phone-list]
              [re-frame-examples.phonecat.views.phone-details]
              [re-frame-examples.phonecat.routes :refer [phonecat-routes]]))

(defn phonecat-panel
  "defines the one page which will be the phone list component"
  []
  (phonecat-routes)
  (rf/dispatch [:initialize-phonecat-state])
  (rf/dispatch [:load-phones])
  (phone-list/home-page))

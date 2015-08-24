(ns re-frame-examples.phonecat.core
    (:require [reagent.core :as r]
              [re-frame.core :as rf]
              [re-frame-examples.phonecat.subs]
              [re-frame-examples.phonecat.handler]
              [re-frame-examples.phonecat.views.phone-list]
              [re-frame-examples.phonecat.views.phone-details]
              [re-frame-examples.phonecat.routes :as routes]
              ))


;; Initialize app
(defn init! []
  (routes/hook-browser-navigation!)
  (rf/dispatch [:initialise-db])
  (rf/dispatch [:load-phones])
  (r/render-component [routes/current-page] (.getElementById js/document "app")))

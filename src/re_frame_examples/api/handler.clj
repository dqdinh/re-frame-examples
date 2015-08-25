(ns re-frame-examples.api.handler
  (:require [compojure.core :refer [GET defroutes routes]]
            [selmer.parser :refer [render-file]]
            [ring.middleware.file-info :refer [wrap-file-info]]
            ;; [ring.middleware.defaults :refer [site-defaults wrap-defaults]]
            [compojure.handler :as handler]
            [environ.core :refer [env]]
            [compojure.route :as route]))

(defn init []
  (println "lala is starting"))

(defn destroy []
  (println "lala is shutting down"))

(defroutes app-routes
  (GET "/" [] (render-file "public/index.html" {}))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (-> (routes app-routes)
      (handler/site)))

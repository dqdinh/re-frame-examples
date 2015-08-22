(ns re-frame-examples.utils)

(defn mlog [& messages]
  (.apply (.-log js/console) js/console (clj->js messages)))

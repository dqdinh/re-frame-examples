(ns re-frame-examples.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as rf]))

(rf/register-sub
 :initialized?
 (fn [state]
   (reaction (not (empty? @state)))))

(rf/register-sub
 :active-panel
 (fn [state _]
   (reaction (:active-panel @state))))

(rf/register-sub
 :panel-params
 (fn [state _]
   (reaction (:panel-params @state))))

(rf/register-sub
 :debug-state
 (fn [state _]
   (reaction @state)))

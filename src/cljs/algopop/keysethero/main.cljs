(ns algopop.keysethero.main
  (:require
    [reagent.core :as reagent]
    [algopop.keysethero.model :as model]
    [algopop.keysethero.views.root :as main]
    [devcards.core]
    [goog.events :as events]
    [goog.history.EventType :as EventType])
  (:require-macros
    [devcards.core :refer [defcard]])
  (:import
    [goog History]))

(defcard
  "# Keyset Hero")

(enable-console-print!)

(defn render []
  (when-let [element (.getElementById js/document "container")]
    (reagent/render-component
      [main/root model/app-state]
      element)))

(defn navigation [event]
  (model/set-route! (.-token event)))

(defn init []
  (doto (History.)
    (events/listen EventType/NAVIGATE navigation)
    (.setEnabled true))
  (render))

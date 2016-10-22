(ns algopop.keysethero.views.freestyle
  (:require
    [algopop.keysethero.keyboard :as keyboard]
    [algopop.keysethero.model :as model]
    [algopop.keysethero.views.keypad :as keypad]
    [devcards.core])
  (:require-macros
    [devcards.core :refer [defcard-rg]]
    [reagent.core :refer [with-let]]))

(defn freestyle [params]
  (with-let [keyboard (doto (keyboard/create)
                        (keyboard/listen))]
    [:div
     [:h7 "Freestyle'n"]
     [keypad/keypad]
     [:div
      (:text @model/app-state)]]
    (finally
      (keyboard/unlisten keyboard))))

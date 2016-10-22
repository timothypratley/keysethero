(ns algopop.keysethero.devcards
  (:require
    [devcards.core]
    [algopop.keysethero.main]
    [algopop.keysethero.firebase :as firebase])
  (:require-macros
    [devcards.core :refer [start-devcard-ui! defcard-rg]]))

(enable-console-print!)

(defonce firebase (firebase/init))

(defn init []
  (start-devcard-ui!))

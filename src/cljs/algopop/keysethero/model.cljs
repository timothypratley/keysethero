(ns algopop.keysethero.model
  (:require [reagent.core :as reagent]
            [cljs.tools.reader.edn :as edn]
            [clojure.walk :as walk]))

(defonce app-state
  (reagent/atom {}))

(defn set-route! [token]
  (swap! app-state assoc :route token))

(defn maybe-update [m k f]
  (if (contains? m k)
    (update m k f)
    m))

(def colors
  ["black"
   "#181818"
   "#282828"
   "#383838"
   "#585858"
   "#b8b8b8"
   "#d8d8d8"
   "#e8e8e8"
   "#f8f8f8"
   "#ab4642"
   "#dc9656"
   "#f7ca88"
   "#a1b56c"
   "#86c1b9"
   "#7cafc2"
   "#ba8baf"
   "#a16946"])

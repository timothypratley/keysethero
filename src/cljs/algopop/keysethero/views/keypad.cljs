(ns algopop.keysethero.views.keypad)

(defn keypad []
  [:table
   [:thead]
   (into
     [:tbody]
     (for [row (range 4)]
       (into
         [:tr]
         (for [column (range 2)]
           [:td
            [:button.mdl-button.mdl-js-button.mdl-button--fab.mdl-js-ripple-effect.mdl-button--colored
             (str row column)]]))))])
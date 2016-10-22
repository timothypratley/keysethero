(ns algopop.keysethero.keyboard
  (:require
    [algopop.keysethero.model :as model]
    [goog.events :as events]
    [goog.events.KeyCodes :as KeyCodes]))

(def key-bindings
  {1 #{#{KeyCodes/A}}
   2 #{#{KeyCodes/S}}
   3 #{#{KeyCodes/D}}
   4 #{#{KeyCodes/F}}
   5 #{#{KeyCodes/J}}
   6 #{#{KeyCodes/K}}
   7 #{#{KeyCodes/L}}
   8 #{#{KeyCodes/SEMICOLON}}})

(defn pressed? [key-codes codes]
  (every? #(aget key-codes %) codes))

(defn handle-keyboard [key-codes]
  (swap! model/app-state update :text conj
         (reduce
           (fn [acc [idx codes]]
             (cond-> acc
               (pressed? key-codes (first codes)) (conj idx)))
           #{}
           key-bindings)))

(defn create []
  (let [key-codes #js {}]
    {:codes key-codes
     :handler
     (fn on-key-change [e]
       (aset key-codes (.-keyCode e) (= (.-type e) "keydown"))
       (handle-keyboard key-codes))}))

(defn listen
  ([o] (listen o js/document))
  ([{:keys [handler] :as o} dom-element]
   (set! (.-dom-element o) dom-element)
   (events/listen dom-element "keydown" handler)
   (events/listen dom-element "keyup" handler)))

(defn unlisten [{:keys [handler] :as o}]
  (let [dom-element (.-dom-element o)]
    (events/unlisten dom-element "keydown" handler)
    (events/unlisten dom-element "keyup" handler)))
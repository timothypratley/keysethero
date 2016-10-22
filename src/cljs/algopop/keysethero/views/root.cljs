(ns algopop.keysethero.views.root
  (:require
    [algopop.keysethero.model :as model]
    [algopop.keysethero.views.login :as login]
    [algopop.keysethero.views.freestyle :as freestyle]
    [algopop.keysethero.views.script :as script]
    [bidi.bidi :as bidi]
    [cljsjs.material]
    [clojure.string :as string]))

(defn home [params]
  [:div
   [:div.mdl-grid
    [:div.mdl-cell.mdl-cell--6-col
     [:div.mdl-card.mdl-shadow--2dp
      {:style {:width "100%"}}
      [:div.mdl-card__title
       [:h2.mdl-card__title-text "Welcome"]]
      [:div.mdl-card__supporting-text
       [:ul {:style {:list-style "none"}}
        [:li "Draw your ideas as you think them."]
        [:li "Ideas are saved in the cloud."]
        [:li "Share and browse."]]
       [:em "\"Everyone is born creative. Everyone is given a box of crayons in kindergarten.\""]
       " -- Hugh MacLeod"]
      [:div.mdl_card__action.mdl-card--border
       [:a.mdl-button.mdl-button--colored
        {:href "#/draw/new"
         :style {:box-shadow "inset -1px -1px 0 #3f51b5"
                 :border-radius "20px"}}
        "Get Started"]]]
     [:center
      "Please send me feedback: "
      [:a {:href "mailto:timothypratley@gmail.com"}
       "timothypratley@gmail.com"]]]
    [:div.mdl-cell.mdl-cell--6-col
     [:img {:src "brand.jpg"
            :style {:width "100%"}}]]]])

(def routes
  [""
   [["/" {"home" home
          "freestyle" freestyle/freestyle
          "script" script/script}]
    [true home]]])

(defn navbar [handler]
  (let [anchors
        (doall
          (for [[p h] [["Home" home]
                       ["Freestyle" freestyle/freestyle]
                       ["Script" script/script]]
                :let [title (string/capitalize
                              (if (sequential? p)
                                (first p)
                                p))]]
            [:a.mdl-navigation__link.mdl-button.mdl-button--accent
             {:key title
              :href (str "#" (bidi/path-for routes h))
              :style {:box-shadow (when (= h handler)
                                    "inset 0 -3px 0 #ff4081")}}
             title]))]
    ;; TODO: improve small screen styling
    [:header
     [:div
      [:div.mdl-layout-spacer]
      [:nav
       {:style {:border-bottom "1px solid lightgrey"
                :margin-bottom "20px"}}
       anchors
       [:a.mdl-navigation__link.mdl-button.mdl-button--colored
        {:href (str "#" (bidi/path-for routes home))
         :style {:border-radius "20px"
                 :box-shadow "inset -1px -1px 0 #3f51b5"}}
        [:i.material-icons "\uE254"]
        " Start"]
       [login/login-view]]]]))

(defn root [app-state]
  (let [{:keys [handler route-params]} (bidi/match-route routes (:route @app-state))]
    [:div
     [:div.mdl-layout.mdl-layout--fixed-header
      [navbar handler]
      [:main
       [:section
        [handler route-params]]]]]))

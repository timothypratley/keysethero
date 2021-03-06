(set-env!
 :source-paths    #{"src/cljs"}
 :resource-paths  #{"resources"}
 :dependencies '[[adzerk/boot-cljs          "1.7.228-2"  :scope "test"]
                 [adzerk/boot-reload        "0.4.13"      :scope "test"]
                 [pandeiro/boot-http        "0.7.3"      :scope "test"]
                 [org.clojure/clojure "1.9.0-alpha13"]
                 [org.clojure/clojurescript "1.9.293"]
                 [reagent "0.6.0"]
                 [devcards "0.2.2"]
                 [bidi "2.0.13"]
                 [cljsjs/firebase "3.3.0-0"]
                 [cljsjs/material "1.2.1-0"]
                 [cljsjs/google-platformjs-extern "1.0.0-0"]])

(require
 '[adzerk.boot-cljs      :refer [cljs]]
 '[adzerk.boot-reload    :refer [reload]]
 '[pandeiro.boot-http    :refer [serve]])

(deftask build []
  (comp ;;(speak)
        (cljs)))

(deftask run []
  (comp (serve)
        (watch)
        (reload)
        (build)))

(deftask production []
  (task-options! cljs {:optimizations :advanced
                       :source-map true})
  identity)

(deftask development []
  (task-options! cljs {:optimizations :none
                       :source-map true}
                 reload {:on-jsload 'algopop.keysethero.main/render})
  identity)

(deftask dev
  "Simple alias to run application in development mode"
  []
  (comp (development)
        (run)))

(deftask public []
  (comp (production)
        (build)
        (sift :invert true :include #{#"js/devcards\.out" #"js/app\.out" #"\S+\.cljs\.edn"})
        (target :dir #{"public"})))

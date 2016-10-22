(defproject
  boot-project
  "0.0.0-SNAPSHOT"
  :comment
  "Generated from build.boot for Cursive"
  :dependencies
  [[adzerk/boot-cljs "1.7.228-2" :scope "test"]
   [adzerk/boot-reload "0.4.13" :scope "test"]
   [pandeiro/boot-http "0.7.3" :scope "test"]
   [org.clojure/clojure "1.9.0-alpha13"]
   [org.clojure/clojurescript "1.9.293"]
   [reagent "0.6.0"]
   [devcards "0.2.2"]
   [bidi "2.0.13"]
   [cljsjs/firebase "3.3.0-0"]
   [cljsjs/material "1.2.1-0"]
   [cljsjs/google-platformjs-extern "1.0.0-0"]]
  :repositories
  [["clojars" {:url "https://clojars.org/repo/"}]
   ["maven-central" {:url "https://repo1.maven.org/maven2"}]]
  :source-paths
  ["src/cljs" "resources"])
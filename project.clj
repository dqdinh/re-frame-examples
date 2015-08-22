(defproject re-frame-examples "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.48"]
                 [reagent "0.5.0"]
                 [reagent-forms "0.5.6"]
                 [reagent-utils "0.1.5"]
                 [re-frame "0.4.1"]
                 [re-com "0.6.1"]
                 [cljs-ajax "0.3.14"]
                 [ring "1.4.0"]
                 [ring/ring-defaults "0.1.3"]
                 [prone "0.8.2"]
                 [selmer "0.9.0"]
                 [environ "1.0.0"]
                 [compojure "1.4.0"]
                 [secretary "1.2.3"]]

  :plugins [[lein-cljsbuild "1.0.6"]
            [lein-figwheel "0.3.7" :exclusions [cider/cider-nrepl]]]

  :source-paths ["src"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target" "test/js"]

  :cljsbuild {:builds [{:id "dev"
                        :source-paths ["src"]

                        :figwheel {:on-jsload "re-frame-examples.core/mount-root"}

                        :env {:dev? true}

                        :compiler {:main re-frame-examples.core
                                   :output-to "resources/public/js/compiled/app.js"
                                   :output-dir "resources/public/js/compiled/out"
                                   :asset-path "js/compiled/out"
                                   :pretty-print true
                                   :optimizations :none
                                   :source-map true
                                   :source-map-timestamp true}}

                       {:id "test"
                        :source-paths ["src" "test"]
                        :notify-command ["phantomjs" "test/unit-test.js" "test/unit-test.html"]
                        :compiler {:optimizations :whitespace
                                   :pretty-print true
                                   :output-to "test/js/app_test.js"
                                   :warnings {:single-segment-namespace false}}}

                       {:id "min"
                        :source-paths ["src"]
                        :env {:dev? false}
                        :compiler {:main re-frame-examples.core
                                   :output-to "resources/public/js/compiled/app.js"
                                   :optimizations :advanced
                                   :pretty-print false}}]}

  :figwheel {
             ;; :http-server-root "public" ;; default and assumes "resources"
             :server-port 8080 ;; default
             ;; :server-ip "127.0.0.1"

             :css-dirs ["resources/public/css"] ;; watch and update CSS

             ;; Start an nREPL server into the running figwheel process
             ;; :nrepl-port 7888

             ;; Server Ring Handler (optional)
             ;; if you want to embed a ring handler into the figwheel http-kit
             ;; server, this is for simple ring servers, if this
             ;; doesn't work for you just run your own server :)
             ;; :ring-handler frontend.core/handler

             ;; To be able to open files in your editor from the heads up display
             ;; you will need to put a script on your path.
             ;; that script will have to take a file path and a line number
             ;; ie. in  ~/bin/myfile-opener
             ;; #! /bin/sh
             ;; emacsclient -n +$2 $1
             ;;
             ;; :open-file-command "myfile-opener"

             ;; if you want to disable the REPL
             ;; :repl false

             ;; to configure a different figwheel logfile path
             ;; :server-logfile "tmp/logs/figwheel-logfile.log"
             })

(ns test-clojure.core
  (:gen-class))

;; helper utils
(require '[clojure.repl :refer :all])
;;debugging parts of expressions
(defmacro dbg[x] `(let [x# ~x] (println "dbg:" '~x "=" x#) x#))

;;-----------------------------------------------------------------------

(class 1)
(class 1.)

'(+ 1 2)
(quote (+ 1 2))
(eval '(+ 1 2))

(list 1 2 3)

(range 10)
;(range) ; => (0 1 2 3 4 ...) (an infinite series)
(take 4 (range))

(cons 4 [1 2 3])
(cons 4 '(1 2 3))
(conj [1 2 3] 4)
(conj '(1 2 3) 4)

(defn hello [greeting & who]
  (println greeting (first who)))

(dbg (hello "hello" "world"))

(Math/sqrt 25)

(defn greet [] (println "hi"))

(def greet (fn [] (println "hi")))

(def greet #(str "hi"))

(dbg (greet))
(dbg (= "hi" (greet)))

(defn my-identity [x] x)
(my-identity 123)

(defn always-thing [& args] :thing)
(always-thing 33 44)

(defn func1 [msg] (println (str msg)))
(defn triplicate [f] (f) (f) (f))
;; (triplicate func1)

(defn triplicate2 [f & args]
    (triplicate (fn [] (apply f args))))

(triplicate2 func1 "hello")

(assert (== -1 (Math/cos Math/PI)))
(defn sin-plus-cos-square [x] (+ (Math/pow (Math/sin x) 2) (Math/pow (Math/cos x) 2) ))
(sin-plus-cos-square 35)

(slurp "http://www.baidu.com")

(println (slurp "D:\\1.txt"))

(defmacro my-first-macro []
  (list reverse "Hello World"))

(my-first-macro)
; (println (reverse "Hello World"))

;; (doc partial)

(def show-str   
  (partial #(str % %2 %3) 11 22)  
)  

(show-str 33)  
;= '112233'


(defn testFunc [& args] (println (first args)))
(testFunc 11 22 33)

(defn make-thingy [x] (fn [& args] x))
((make-thingy "any") 22 33)

(let [n (rand-int Integer/MAX_VALUE)
      f (make-thingy n)]
  (assert (= n (f)))
  (assert (= n (f :foo)))
  (assert (= n (apply f :foo (range)))))

(doc complement)

(defn divisible-by-3-or-5 [num] (or (== (mod num 3) 0)(== (mod num 5) 0))) 
(divisible-by-3-or-5 4)


(import java.util.Date)

; Use "use" to get all functions from the module
(use 'clojure.set)

; test作为当前的namespace，这样后续代码可以直接使用Date，Calendar类
(ns test
  (:import java.util.Date
           java.util.Calendar))

(Date.)

(import java.util.Calendar)
(doto (Calendar/getInstance)
  (.set 2000 1 1 0 0 0) (.getTime))


(def my-atom (atom {:b 2}))  
my-atom

(swap! my-atom assoc :a 1)
my-atom

@my-atom

(def counter (atom 0))
(defn inc-counter []
  (swap! counter inc))

(inc-counter)


(defmacro unless [arg & body]
  `(if (not ~arg)
     (do ~@body))) 

(macroexpand '(unless true (reverse "Hello World")))
(unless false 22 33 44)
(do 33 44)

(defmacro define-x []
  '(do
     (def x 2)
     (list x)))

(def x 4)
x
(define-x) ; -> (2)
(list x) ; -> (2)


(println (gensym 'z))


; Now we can use set operations
(intersection #{1 2 3} #{2 3 4}) ; => #{2 3}
(difference #{1 2 3} #{2 3 4}) ; => #{1}

(require 'clojure.string)

(clojure.string/blank? "ddd")


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))



    
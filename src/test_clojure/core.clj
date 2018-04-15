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


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))



    
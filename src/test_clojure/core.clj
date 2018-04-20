(ns test-clojure.core
  (:gen-class))

;; helper utils
(require '[clojure.repl :refer :all])
;;debugging parts of expressions
(defmacro dbg[x] `(let [x# ~x] (println "dbg:" '~x "=" x#)))
;;(defmacro dbg[x] `(let [x# ~x] (println "dbg:" '~x "=" x#) x#))

;;-----------------------------------------------------------------------

(defn fac [n]
  (if (= 1 n)
    1
    (+ n (fac (dec n)))))


(defn fac [n]
   (defn fac0 [c r]
      (if (= 0 c)
          r
          (recur (dec c) (+ c r))))
   (fac0 n 1))

(fac 10000)

(defn fib [n]
  (loop [fib-nums [0 1]]
    (if (>= (count fib-nums) n)
      (subvec fib-nums 0 n)
      (let [[n1 n2] (reverse fib-nums)]
        (recur (conj fib-nums (+ n1 n2)))))))

(fib 10)


(defn LoopPrint [endValue] (loop [a 0] (if (< a endValue) (do (println a) (recur (+ 1 a)))) ) )
(LoopPrint 100)


( loop [a 0] (if (< a 10) (do (println a) (recur (+ 1 a)))) )


(defn countdown [result x] 
  (if (zero? x)
    result
    (recur (conj result x) (dec x)))
)

(countdown [] 100)

(do (conj [] 3) (dec 3))

(let [x 5]
  (cond
    (< x 2) "x is less than 2"
    (< x 10) "x is less than 10"))

(if (even? 5)
  (do (println "even")
      true)
  (do (println "odd")
      false))

(let [x 11]
    (cond
      (< x 2)  "x is less than 2"
      (< x 10) "x is less than 10"
      :else  "x is greater than or equal to 10"))

(defn foo [x]
    (case x
       5 "x is 5"
       10 "x is 10"
       "x isn't 5 or 10" ))

(foo 1)

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

(conj [1 2 3] 4) 

  (conj '(1 2 3) 4) 

  (filter #(< % 5) '(1 22 3 43 5 9)) 

  (defn plus [& args] ( apply #(+ %1 %2) args ) )
  (apply plus [1 3])

  (reduce + 6 [ 1 2 3])

  (reduce conj [] '(3 2 1))

  (fn [] "Hello World")

  (class {:a 1 :b 2 :c 3}) 

  (def stringmap {"a" 1, "b" 2, "c" 3})
  (def x 555)
  x

  (def newkeymap (assoc keymap :d 4))

  keymap
  newkeymap

  (assoc keymap :d 4)

  (->  
    {:a 1 :b 2} 
    (assoc :c 3) ;=> (assoc {:a 1 :b 2} :c 3)
    (dissoc :b))

  (into '(1 2) [3 4 5 6 7])  

  (map concat ["xie"] ["spark" "jim" "tom"])

  (->>
      (range 10)
      (map inc)     ;=> (map inc (range 10)
      (filter odd?) ;=> (filter odd? (map inc (range 10))
      (into []))    

  (into [1 2 3] '(4 5 6))
  (cons '(1 2 3) '(4 5 6))
  (conj '(1 2 3) '(4 5 6))

  (def cards '(10 :ace :jack 9))
  (def cards '(:ace 10 :jack 9))
  cards

  (def stack '(:a :b :c))
  stack
  (peek stack)
  (pop stack)
  stack

  (defmacro my-first-macro []
    (list reverse "Hello World"))

  (my-first-macro)

  (macroexpand '(my-first-macro))
  (eval (macroexpand '(my-first-macro)))

  (defmacro my-first-quoted-macro []
    '(reverse "Hello World"))

  (my-first-quoted-macro)

  (defmacro inc2 [arg]
    (list + 2 arg))

  (inc2 2)

  (defmacro inc2-quoted [arg]
    `(+ 2 ~arg))

  (inc2-quoted 3)

  (assoc [1 2 3 4 5] 0 0)
  
  (->> [1 2 3]   
    (map inc)        ; (map inc [1 2 3])
    (map inc)        ; (map inc (2 3 4))
    (concat [0 2]))  ; (concat [0 2] (3 4 5))

  (-> [1 2 3]
    (conj 4)         ; (conj [1 2 3] 4)
    (conj 5)         ; (conj [1 2 3 4] 5)
    (assoc 0 0))     ; (assoc [1 2 3 4 5] 0 0)    

  (as-> [1 2 3] input
    (map inc input);=> You can use last transform's output at the last position
    (nth input 2) ;=>  and at the second position, in the same expression
    (conj [4 5 6] input [8 9 10])) 

(doseq [l (map #(str "Happy Birthday " (if (= % 2) "dear Rich" "to You")) (range 4))] (println l))      

(def fib-seq (lazy-cat [0 1] (map + fib-seq (rest fib-seq))))

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



    
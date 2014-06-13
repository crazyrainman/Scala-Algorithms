package org.gs.graph
/**
 * @see http://algs4.cs.princeton.edu/41undirected/BreadthFirstPaths.java.html
 */
import scala.collection.mutable.Queue
import scala.collection.mutable.ListBuffer
import scala.annotation.tailrec

/**
 * @author Gary Struthers
 * @param g
 * @param s
 */
class BreadthFirstPaths(g: Graph, s: Int) {
  private[graph] val marked = Array.fill[Boolean](g.v)(false)
  private[graph] val edgeTo = new Array[Int](g.v)
  private[graph] val _distTo = Array.fill[Int](g.v)(Int.MaxValue)

  private def bfs(s: Int): Unit = {
    val q = new Queue[Int]()
    _distTo(s) = 0
    marked(s) = true
    q.enqueue(s)
    for {
      v <- q
      w <- g.adj(v)
      if (!marked(w))
    } {
      edgeTo(w) = v
      _distTo(w) = _distTo(v) + 1
      marked(w) = true
      q.enqueue(w)
    }
  }
  bfs(s)

  def hasPathTo(v: Int): Boolean = marked(v)

  def distTo(v: Int): Int = _distTo(v)

  def pathTo(v: Int): Option[List[Int]] = {
    val path = ListBuffer[Int]()
    if (!hasPathTo(v)) None else {
      def loop(x: Int): Unit = {
        if (distTo(x) == 0) {
          path.prepend(x)
          val y = edgeTo(x)
          loop(y)
        }
        path.prepend(v)
      }
      loop(v)
      Some(path.toList)
    }
  }
}

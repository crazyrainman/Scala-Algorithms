package org.gs.digraph

/**
 * @see http://algs4.cs.princeton.edu/42directed/DepthFirstDirectedPaths.java.html
 */
import scala.annotation.tailrec

/**
 * @author Gary Struthers
 *
 */
class DepthFirstDirectedPaths(g: Digraph, s: Int) {
  private val marked = new Array[Boolean](g.v)
  private val edgeTo = new Array[Int](g.v)

  private def dfs(v: Int) {
    marked(v) = true
    for {
      w <- g.adj(v)
      if (!marked(w))
    } {
      edgeTo(w) = v
      dfs(w)
    }
  }
  dfs(s)

  def hasPathTo(v: Int): Boolean = marked(v)

  def pathTo(v: Int): List[Int] = {
    var pathStack = List[Int]()
    @tailrec
    def loop(x: Int): Int = {
      if (x == s) x else {
        pathStack = x :: pathStack
        loop(edgeTo(x))
      }
    }
    loop(v) :: pathStack
  }
}

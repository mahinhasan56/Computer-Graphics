/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package astar;

import java.util.ArrayList;

/**
 *
 * @author Dipro
 */
class Q
{
    public ArrayList<String> queue = new ArrayList<String>();
    
    public boolean enque(String s)
    {
        if(queue.contains(s))
        {
            return false;
        }
        queue.add(s);
        return true; 
    }

    public int priority()
    {
        double [] d = AStar.distance;
        double [] c = AStar.cost;
        String [] p = AStar.parent;
        
        ArrayList<String> v = AStar.vertices;
        double value = 0;
        int reply = 0;
        
        if(p[v.indexOf(queue.get(0))]==null)
        {
            value = c[v.indexOf(queue.get(0))];
        }
        else
        {
            for(int i=0; i<queue.size(); i++)
            {
                if(value>d[v.indexOf(queue.get(i))]+c[v.indexOf(queue.get(i))]) //f(n) = g(n) + h(n)
                {
                    value = d[i]+c[v.indexOf(queue.get(i))];
                    reply = i;
                }
            }
        }
        System.out.print(queue.get(reply)+" |");
        return reply;
    }
    
    public String deque()
    {
        if(!queue.isEmpty())
        {
            String temp = queue.get(priority());
            queue.remove(temp);
            return temp;
        }
        return null;
    }

    public String peek()
    {
        if(!this.isEmpty())
        {
            return queue.get(0);
        }
        return null;
    }

    public int size()
    {
        return queue.size();
    }

    public boolean isEmpty()
    {
        return queue.isEmpty();
    }
    
    public String  print()
    {
        String str = "";
        for (String temp : queue)
        {
            str = str +" "+ temp;
        }
        return str+" |";
    }
}
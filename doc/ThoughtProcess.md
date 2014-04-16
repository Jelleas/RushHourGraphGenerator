Rush Hour Thought Process
==========

* Rush Hour boards are grouped in clusters of reachable boards. Each board occurs in only one cluster, and is solvable if there exists a board within the cluster that is in the solved state, meaning that the red car is directly in front of the exit.

* The structure of clusters containing hard to solve boards, where hard is the minimum number of steps to the nearest solve state, might have a set of distinctive features that sets it apart from clusters containing only easy to solve boards.

* The state space of Rush Hour boards is limited, roughly 13 billions boards where 2/3 are unsolvable. Thus 5 billion interesting boards. Earlier results from a different study managed to construct graphs for these 5 billion states within reasonable time (20 hours), thus I could attempt the same. A rough estimate of 10.000 nodes per cluster gives 500.000 clusters, thus earlier results managed to compute each cluster within 1/7th of a second!

* Thanks to some clever representation techniques a board including index can be represented in just 20 bytes, and only 16 bytes are needed per accesibility relation between boards. Thus, the database of clusters should (only) have a size of up to 1TB given a crude estimation of an avarage branchfactor of 5 (based on absolutly nothing but gut feeling and experience).

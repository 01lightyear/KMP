package KMP;

public class KMP {
    public int[] getNext(String target){
        int []next=new int[target.length()];
        next[0]=-1;
        int i=0,j=-1;
        while(i<target.length()-1){//form the partial match table
            if(j==-1||target.charAt(i)==target.charAt(j)){
                i++;
                j++;
                next[i]=j;// When a match is found, record the length of the matching prefix
                // This value will be used as a fallback position when a mismatch occurs 
            }
            else{
                j=next[j];// When mismatch occurs, instead of moving back to the beginning,
                // we utilize the partial match information we already have
                // next[j] tells us where to continue the comparison based on 
                // the longest prefix that matches the suffix in the already matched portion
            }
        }
        return next;
    }
    public int KMP(String source, String target){
        int i=0,j=0;
        int []next=getNext(target);
        while(i<source.length()&&j<target.length()){
            if(j==-1||source.charAt(i)==target.charAt(j)){
                i++;
                j++;
            }else{
                j=next[j];
            }
        }
        if(j==target.length())
            return i-j;
        else
            return -1;
    }

    public static void main(String[] args) {
        String a="ababcabcacbab";
        String b="abcac";
        KMP kmp=new KMP();
        System.out.println(kmp.KMP(a, b));
    }
}

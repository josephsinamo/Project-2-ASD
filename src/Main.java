import dsa.sHashMap;
import dsa.sLinkedList;
import dsa.sTree;
import dsa.sHashMap.Pair;
import dsa.sLinkedList.Node;

public class Main {
    public static void main(String[] args) throws Exception {
        String pathDataMatakuliah = "data/prasyarat.csv";
        String pathDataPrasyarat = "data/prasyarat.csv";
        sLinkedList <sTree> list = inisialisasi(pathDataMatakuliah, pathDataPrasyarat);

        list.print();
    }

    public static sLinkedList <sTree> inisialisasi(String pathMatakuliah, String pathPrasyarat) throws Exception{
        sLinkedList <sTree> list = new sLinkedList<>();
        sLinkedList <Prasyarat> listPraSyarat = Prasyarat.makePrasyarat(pathMatakuliah);
        sHashMap <String, sTree> mapMataKuliah = Matakuliah.makeMataKuliah(pathPrasyarat);

        sLinkedList<Prasyarat>.Node  node = listPraSyarat.getHead();
        while(node != null){
            Prasyarat prasyarat = node.getData();
            sTree root = mapMataKuliah.get(prasyarat.getPraSyarat());
            sTree child = mapMataKuliah.get(prasyarat.getKodeMk());
            root.add(child);

            node = node.next;
        }

        for(sLinkedList <sTree> l : mapMataKuliah.getList()){
            Node n = l.getHead();
            while(n != null){
                Pair pair= (Pair)n.data;
                // System.out.println(n);
                // System.out.println(pair);
                if (!((sTree)pair.getValue()).getIsRoot()){
                    mapMataKuliah.remove((String)pair.getKey());
                }
                n = n.next;
            }
        }
        
        list = mapMataKuliah.makeValueList();
        return list;
    }
}

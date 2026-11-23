├── data/
│   ├── matakuliah.csv      # kode;nama;sks;semester;ganjil/genap
│   └── prasyarat.csv       # prasyarat;matakuliah
├── input/
│   └── mhs_XX.txt          # data mahasiswa (satu file per mahasiswa)
├── src/
│   ├── Main.java           # entry point, pembaca file, pencetak output
│   ├── DataStructures/
│   │   ├── Graph.java      # directed graph + DFS prasyarat
│   │   ├── HashMap.java    # hash table (separate chaining)
│   │   ├── LinkedList.java # singly linked list
│   │   └── Node.java       # node linked list
│   └── Entity/
│       ├── Course.java         # data mata kuliah
│       └── BlockedCourse.java  # mata kuliah yang belum bisa diambil + alasannya
└── Instruksi (1).pdf
package com.company;

public class Main {

    public static Activity[][] sch = new Activity[5][4];


    public static void main(String[] args) {

        Activity fLecture = new Activity("Fysikföreläsning");
        Activity fExc = new Activity("Fysikövning");
        Activity datLecture = new Activity("Datföreläsning");
        Activity datExc = new Activity("Datövning");
        Activity gym = new Activity("Träning");
        Activity TApass = new Activity("Javapass");
        Activity TAprep = new Activity("TA-prep");
        Activity misc = new Activity("<Upptagen>");

        sch[0][1] = fLecture;
        sch[2][1] = fLecture;
        sch[3][1] = fLecture;

        //sch[0][3] = datLecture;
        sch[1][3] = datLecture;
        sch[3][3] = datLecture;

        sch[1][2] = TApass;
        sch[3][2] = TApass;

        sch[1][0] = misc;
        //sch[3][3] = misc;
        sch[4][0] = misc;
        int[][] noGym = new int[2][2];
        noGym[0][0] = 0;
        noGym[0][1] = 0;
        noGym[1][0] = 1;
        noGym[1][1] = 1;

        FindGymTime(gym, noGym);

        FindPhysicsTime(fExc, fLecture);

        FindDatTime(datExc, datLecture);

        FindTAPrep(TAprep, TApass, 3);

        String[] times = new String[]{"8-10", "10-12","13-15","15-17"};
        System.out.printf("%n%-10s %-20s %-20s %-20s %-20s %-20s %n", "Tid:","Måndag:", "Tisdag:", "Onsdag:", "Torsdag:", "Fredag:");
        for(int i = 0; i < 4; i++){
            System.out.printf(" %-10s %-20s %-20s %-20s %-20s %-20s %n", times[i], nameMaybeNull(0, i), nameMaybeNull(1, i), nameMaybeNull(2, i), nameMaybeNull(3, i), nameMaybeNull(4, i));
        }

    }

    public static String nameMaybeNull(int d, int t){
        if(sch[d][t] == null){
            return "[]";
        }
        return sch[d][t].name;
    }
    public static void FindGymTime(Activity gym, int[][] noGym){
        int nextGymDay = 0;
        int[] times = new int[4];
        for(int i = 0; i < 4; i++){
            times[i] = -1;
        }
        boolean break2 = false;
        for(int i = 0; i < 2; i++){
            //Searching the morning...
            Boolean foundGym = false;
            for(int d = nextGymDay; d < 5; d++){
                for(int t = 0; t < 2; t++){
                    if(sch[d][t] == null){
                        break2 = false;
                        for(int e = 0; e < noGym.length; e++){
                            if(noGym[e][0] == d && noGym[e][1] == t){
                                break2 = true;
                            }
                        }
                        if(!break2){
                            sch[d][t] = gym;

                            nextGymDay = d + 2;
                            foundGym = true;
                            break;
                        }

                    }
                }
                if(foundGym){
                    break;
                }
            }

            //Searching 3PM...
            if(!foundGym){
                for(int d = nextGymDay; d < 5; d++){
                    if(sch[d][3] == null){
                        sch[d][3] = gym;
                        nextGymDay = d + 2;
                        foundGym = true;
                        break;
                    }
                }
            }


            //Searching 1PM...
            if(!foundGym){
                for(int d = nextGymDay; d < 5; d++){
                    if(sch[d][2] == null){
                        sch[d][2] = gym;
                        nextGymDay = d + 2;
                        foundGym = true;
                        break;
                    }
                }
            }
        }
        return;
    }

    //This function is kind of broken ATM
    public static void FindPhysicsTime(Activity fExc, Activity fLec){
        for(int d = 0; d < 5; d++){
            for(int t = 0; t < 4; t++){
                if(sch[d][t] == fLec){
                    if(d < 4){
                        if(sch[d+1][0] == null){
                            sch[d+1][0] = fExc;
                        } else if(sch[d+1][1] == null){
                            sch[d+1][1] = fExc;
                        } else if(sch[d][Math.min(t+1,3)] == null && t < 3){
                            sch[d][t+1] = fExc;
                        }
                        else if(sch[d][Math.min(t+2,3)] == null && t < 2){
                            sch[d][t+2] = fExc;
                        } else{
                            for(int d2 = d; d2 < 5; d2++){
                                for(int t2 = t; t2 < 4; t2++){
                                    if(sch[d2][t2] == null){
                                        sch[d2][t2] = fExc;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void FindTAPrep(Activity prep, Activity TApass, int amount){
        for(int d = 0; d < 5 && amount > 0; d++){
            for(int t = 0; t < 4 && amount > 0; t++){
                if(sch[d][t] == null){
                    sch[d][t] = prep;
                    amount--;
                } else if(sch[d][t] == TApass){
                    amount = 0;
                    break;
                }
            }
        }
        amount = 1;
        for(int t = 0; t < 4 && amount > 0; t++){
            if(sch[4][t] == null) {
                sch[4][t] = prep;
                amount--;
            }
        }
    }

    public static void FindDatTime(Activity datExc, Activity datLec){
        int lecCounter = 0;
        Boolean break2 = false;
        for(int d = 0; d < 5 && (!break2); d++){
            for(int t = 0; t < 4 && (!break2); t++){
                if(sch[d][t] == datLec){
                    lecCounter++;
                    if(lecCounter == 3){
                        for(int d2 = d; d2 < 5 && (!break2); d2++){
                            for(int t2 = 0; t2 < 4; t2++){
                                if(d2 == d){
                                    t2 = t;
                                }
                                if(sch[d2][t2] == null){
                                    sch[d2][t2] = datExc;
                                    break2 = true;
                                    break;
                                }
                            }
                        }
                    }

                }
            }
        }
    }

    public static String weekday(int d){
        if(d == 0){
            return "Måndag:";
        } else if(d == 1){
            return "Tisdag:";
        } else if(d == 2){
            return "Onsdag:";
        } else if(d == 3){
            return "Torsdag:";
        } else if(d == 4){
            return "Fredag:";
        } else{
            return "null";
        }
    }
}


class Activity {
    String name;
    Activity(String s){
        name = s;
    }
}

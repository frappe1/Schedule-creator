package com.company;

public class Main {

    public static Activity[][] sch = new Activity[5][4];


    public static void main(String[] args) {

        Activity fLecture = new Activity("Fysikföreläsning");
        Activity fExc = new Activity("Fysikövning");
        Activity datLecture = new Activity("Datastruktföreläsning");
        Activity datExc = new Activity("Datastruktövning");
        Activity gym = new Activity("Träning");
        Activity TApass = new Activity("Javapass");
        Activity TAprep = new Activity("TA-prep");
        Activity misc = new Activity("<Upptagen>");

        sch[0][1] = fLecture;
        sch[2][1] = fLecture;
        sch[3][1] = fLecture;

        sch[0][3] = datLecture;
        sch[1][3] = datLecture;
        sch[3][3] = datLecture;

        sch[1][2] = TApass;

        sch[1][0] = misc;
        sch[3][3] = misc;
        sch[4][0] = misc;

        FindGymTime(gym);

        FindPhysicsTime(fExc, fLecture);

        FindTAPrep(TAprep, TApass, 2);

        //Print schedule
        for(int d = 0; d < 5; d++){
            System.out.print("\n\n%%%  " + weekday(d) + "  %%%%%");
            for(int t = 0; t < 4; t++){
                if(sch[d][t] != null){
                    System.out.print("\n " + sch[d][t].name);
                } else{
                    System.out.print("\n []");
                }

            }
        }
    }

    public static void FindGymTime(Activity gym){
        int nextGymDay = 0;
        int[] times = new int[4];
        for(int i = 0; i < 4; i++){
            times[i] = -1;
        }
        for(int i = 0; i < 2; i++){
            //Searching the morning...
            Boolean foundGym = false;
            for(int d = nextGymDay; d < 5; d++){
                for(int t = 0; t < 2; t++){
                    if(sch[d][t] == null){
                        sch[d][t] = gym;

                        nextGymDay = d + 2;
                        foundGym = true;
                        break;
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

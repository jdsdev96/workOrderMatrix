import random
import csv

def generate_random_array():
    i = 0
    main_array = []
    for i in range(500000):
        rand_wo = get_random_wo()
        main_array.append(rand_wo)
    return main_array


def get_random_wo():
    wo = [0,0,0,0,0,0,0]
    wo[0] = random.randint(188000, 188999)#Work Order number
    wo[4] = random.randint(0, 300)#downtime
    if 0 <= wo[4] <= 50:
        wo[2] = "F"
        wo[1] = 3
    if 51 <= wo[4] <= 100:
        wo[2] = "E"
        wo[1] = 3
    if 101 <= wo[4] <= 150:
        wo[2] = "D"
        wo[1] = 2
    if 151 <= wo[4] <= 200:
        wo[2] = "C"
        wo[1] = 2
    if 201 <= wo[4] <= 250:
        wo[2] = "B"
        wo[1] = 1
    if 251 <= wo[4] <= 300:
        wo[2] = "A"
        wo[1] = 1
    wo[3] = random.randint(-14,14)
    wo[5] = random.randint(0,4)
    return wo


def commit_to_csv(array):
    file_name = "test_wo.csv"
    fields = ["WO#", "PD", "ERT", "Due(Days)", "Downtime"]
    with open(file_name, 'w' , newline="") as csvfile:
        csvwriter = csv.writer(csvfile)
        csvwriter.writerow(fields)
        csvwriter.writerows(array)
    return


def main():
    print('Generating work orders...')
    org_array = generate_random_array()
    print('Writing to csv...')
    commit_to_csv(org_array)
    print('done')


main()

import korail2 as Korail

if __name__ == '__main__':

    Line1 = ['Daejeon', 'Gumi', 'Osong', 'Cheonan-Asan']

    korail = Korail.Korail("12345678", "YOUR_PASSWORD", auto_login=False)
    trains = korail.search_train( '부산', '서울', train_type=Korail.TrainType.KTX, date='20191122', time='050000', include_no_seats=True)

    for train in trains:
        print(train, train.has_general_seat())

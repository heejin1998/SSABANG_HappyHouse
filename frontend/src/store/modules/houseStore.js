import { sidoList, gugunList, dongList, houseList, houseListGeo, houseListGeoWithDeals, houseInfo, houseInfoByName } from "@/api/house.js";

const houseStore = {
    namespaced: true,
    state: {
        sidos: [{ value: null, text: "시도" }],
        guguns: [{ value: null, text: "구군" }],
        dongs: [{ value: null, text: "동" }],
        houses: [],
        house: null,
        filter: {
            price: [0, 850000],
            year: [1960, 2025],
            floor: [1, 67],
        },
    },

    getters: {},

    mutations: {
        SET_SIDO_LIST: (state, sidos) => {
            sidos.forEach((sido) => {
                state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
            });
        },
        SET_GUGUN_LIST: (state, guguns) => {
            guguns.forEach((gugun) => {
                state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
            });
        },
        SET_DONG_LIST: (state, dongs) => {
            dongs.forEach((dong) => {
                state.dongs.push({ value: dong.dongCode, text: dong.dongName });
            });
        },
        CLEAR_SIDO_LIST: (state) => {
            state.sidos = [{ value: null, text: "시도" }];
        },
        CLEAR_GUGUN_LIST: (state) => {
            state.guguns = [{ value: null, text: "구군" }];
        },
        CLEAR_DONG_LIST: (state) => {
            state.dongs = [{ value: null, text: "동" }];
        },
        SET_HOUSE_LIST: (state, houses) => {
            state.houses = houses;
        },
        SET_DETAIL_HOUSE: (state, house) => {
            state.house = house;
        },
        SET_HOUSE_LIST_FILTER: (state, houses) => {
            console.log("SET_HOUSE_LIST_FILTER");
            let newHouses = [];
            out: for (const house of houses) {
                let price = house.recentPrice === null ? null : parseInt(house.recentPrice.replaceAll(",", "").trim());
                let year = house.buildYear;
                if (price == null || state.filter.price[0] > price || state.filter.price[1] < price) continue;
                if (state.filter.year[0] > year || state.filter.year[1] < year) continue;
                for (const houseDeal of house.houseDeals) {
                    if (state.filter.floor[0] > houseDeal.floor || state.filter.floor[1] < houseDeal.floor) continue out;
                }
                newHouses.push(house);
            }
            state.houses = newHouses;
        },
        SET_FILTER: (state, filter) => {
            state.filter = filter;
        },
        INIT_FILTER: (state) => {
            state.filter = {
                price: [0, 850000],
                year: [1960, 2025],
                floor: [1, 67],
            };
        },
        SET_HOUSE_BY_APTCODE: (state, house) => {
            state.house = house;
        },
        SET_HOUSE_INIT: (state) => {
            state.house = null;
        },
    },

    actions: {
        getSido: ({ commit }) => {
            sidoList(
                ({ data }) => {
                    // console.log(data);
                    commit("SET_SIDO_LIST", data);
                },
                (error) => {
                    console.log(error);
                }
            );
        },
        getGugun: ({ commit }, sidoCode) => {
            const params = {
                sido: sidoCode,
            };
            gugunList(
                params,
                ({ data }) => {
                    // console.log(commit, response);
                    commit("SET_GUGUN_LIST", data);
                },
                (error) => {
                    console.log(error);
                }
            );
        },
        getDong: ({ commit }, gugunCode) => {
            const params = {
                gugun: gugunCode,
            };
            dongList(
                params,
                ({ data }) => {
                    // console.log(commit, response);
                    commit("SET_DONG_LIST", data);
                },
                (error) => {
                    console.log(error);
                }
            );
        },
        getHouseList: ({ commit }, dongCode) => {
            // vue cli enviroment variables 검색
            //.env.local file 생성.
            // 반드시 VUE_APP으로 시작해야 한다.
            //const SERVICE_KEY = process.env.VUE_APP_APT_DEAL_API_KEY;
            //   const SERVICE_KEY =
            //     "9Xo0vlglWcOBGUDxH8PPbuKnlBwbWU6aO7%2Bk3FV4baF9GXok1yxIEF%2BIwr2%2B%2F%2F4oVLT8bekKU%2Bk9ztkJO0wsBw%3D%3D";
            const params = {
                dong: dongCode,
            };
            houseList(
                params,
                (response) => {
                    //   console.log(response.data.response.body.items.item);
                    commit("SET_HOUSE_LIST", response.data);
                },
                (error) => {
                    console.log(error);
                }
            );
        },
        getHouseListGeo: ({ commit, rootState }) => {
            console.log("getHouseListGeo Action");
            let map = rootState.mapStore.map;
            // 지도 영역정보를 얻어옵니다
            let bounds = map.getBounds();

            // 영역정보의 남서쪽 정보를 얻어옵니다
            let swLatlng = bounds.getSouthWest();

            // 영역정보의 북동쪽 정보를 얻어옵니다
            let neLatlng = bounds.getNorthEast();
            let geo = {
                swLat: swLatlng.getLat(),
                swLng: swLatlng.getLng(),
                neLat: neLatlng.getLat(),
                neLng: neLatlng.getLng(),
            };

            houseListGeo(
                geo,
                (response) => {
                    commit("SET_HOUSE_LIST", response.data);
                },
                (error) => {
                    console.log(error);
                }
            );
        },
        getHouseListGeoWithDeals: ({ commit, rootState }) => {
            console.log("getHouseListGeoWithDeals Action");
            let map = rootState.mapStore.map;
            // 지도 영역정보를 얻어옵니다
            let bounds = map.getBounds();

            // 영역정보의 남서쪽 정보를 얻어옵니다
            let swLatlng = bounds.getSouthWest();

            // 영역정보의 북동쪽 정보를 얻어옵니다
            let neLatlng = bounds.getNorthEast();
            let geo = {
                swLat: swLatlng.getLat(),
                swLng: swLatlng.getLng(),
                neLat: neLatlng.getLat(),
                neLng: neLatlng.getLng(),
            };
            houseListGeoWithDeals(
                geo,
                (response) => {
                    commit("SET_HOUSE_LIST_FILTER", response.data);
                },
                (error) => {
                    console.log(error);
                }
            );
        },
        detailHouse: ({ commit }, house) => {
            // 나중에 house.일련번호를 이용하여 API 호출
            commit("SET_DETAIL_HOUSE", house);
        },
        setHouseFilter: ({ commit }, filter) => {
            commit("SET_HOUSE_FILTER", filter);
        },
        getHouseInfo: ({ commit }, aptCode) => {
            houseInfo(
                aptCode,
                (response) => {
                    commit("SET_HOUSE_BY_APTCODE", response.data);
                },
                (error) => {
                    console.log(error);
                }
            );
        },
        getHouseInfoByName: ({ commit }, aptName) => {
            houseInfoByName(
                aptName,
                (response) => {
                    commit("SET_HOUSE_LIST", response.data);
                },
                (error) => {
                    console.log(error);
                }
            );
        },
    },
};

export default houseStore;

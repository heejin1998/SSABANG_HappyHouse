/* global kakao */
const mapStore = {
    namespaced: true,
    state: {
        map: null,
        markers: [],
        imageSize: new kakao.maps.Size(32, 32),
        imageOptions: {
            spriteOrigin: new kakao.maps.Point(0, 0),
            spriteSize: new kakao.maps.Size(32, 32),
        },
        activeSrc: require("/src/assets/town_active.png"),
        disableSrc: require("/src/assets/town.png"),
    },
    mutations: {
        SET_MAP: (state, payload) => {
            state.map = payload;
        },
        SET_MARKERS: (state, payload) => {
            state.markers = payload;
        },
        INIT_MARKERS: (state) => {
            state.markers = [];
        },
        UPDATE_MARKER: (state, { house, marker }) => {
            for (let i = 0; i < state.markers.length; i++) {
                if (state.markers[i].aptCode === house.aptCode) {
                    state.markers[i].marker = marker;
                }
            }
        },
    },
    actions: {
        addMarkers: ({ state, commit }, houses) => {
            if (state.map.getLevel() <= 5) {
                //새로운 마커 생성
                let markers = [];

                let markerImage = new kakao.maps.MarkerImage(state.disableSrc, state.imageSize, state.imageOptions);
                for (const house of houses) {
                    let markerPosition = new kakao.maps.LatLng(house.lat, house.lng);
                    let marker = new kakao.maps.Marker({ position: markerPosition, image: markerImage });
                    markers.push({ aptCode: house.aptCode, marker: marker });
                }
                commit("SET_MARKERS", markers);
                for (const { marker } of state.markers) {
                    marker.setMap(state.map);
                }
            }
        },
        removeMarkers: ({ state, commit }) => {
            for (const { marker } of state.markers) {
                marker.setMap(null);
            }
            commit("INIT_MARKERS");
        },
        updateMarker: ({ state, commit }, { house, imgSrc }) => {
            state.markers.forEach((element) => {
                if (element.aptCode === house.aptCode) {
                    let markerImage = new kakao.maps.MarkerImage(imgSrc, state.imageSize, state.imageOptions);
                    let markerPosition = new kakao.maps.LatLng(house.lat, house.lng);
                    let marker = new kakao.maps.Marker({ position: markerPosition, image: markerImage });
                    element.marker.setMap(null);
                    marker.setMap(state.map);
                    commit("UPDATE_MARKER", { house, marker });
                }
            });
        },
    },
};

export default mapStore;

import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
    stages: [
        { duration: '1m', target: 250 },
        { duration: '2m', target: 1000 },
        { duration: '1m', target: 0 },
    ],
};

function randomIntBetween(min, max) {
    return Math.floor(Math.random() * (max - min + 1) + min);
}

function randomElementFromArray(array) {
    return array[Math.floor(Math.random() * array.length)];
}

export default function () {
    const url = 'http://localhost:8080/api/v3/pet';
    const payload = JSON.stringify({
        "id": randomIntBetween(1, 1000000),
        "name": `Pet-${randomIntBetween(1, 100)}`,
        "category": {
            "id": randomIntBetween(1, 5),
            "name": `Category-${randomIntBetween(1, 5)}`
        },
        "photoUrls": [
            `/pet/photo/profile-${randomIntBetween(1, 100)}.jpg`
        ],
        "tags": [
            {
                "id": randomIntBetween(1, 10),
                "name": `Tag-${randomIntBetween(1, 10)}`
            }
        ],
        "status": randomElementFromArray(["available", "pending", "sold"])
    });

    const params = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    let response = http.post(url, payload, params);

    check(response, {
        'is status 200': (r) => r.status === 200,
        'is status not 500': (r) => r.status !== 500,
        'has a reasonable response time': (r) => r.timings.duration <= 2000,
        'time to first byte is short': (r) => r.timings.waiting < 1000
    });

    sleep(1);
}

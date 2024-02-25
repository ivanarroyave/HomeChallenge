import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
    stages: [
        { duration: '30s', target: 5000 },
        { duration: '1m', target: 15000 },
        { duration: '30s', target: 0 },
    ],
};

const STATUS_VALUES = ['available', 'pending', 'sold', 'other'];

export default function () {
    const status = STATUS_VALUES[Math.floor(Math.random() * STATUS_VALUES.length)];
    const res = http.get(`http://localhost:8080/api/v3/pet/findByStatus?status=${status}`);

    check(res, {
        'is status 200': (r) => r.status === 200,
        'is status not 500': (r) => r.status !== 500,
        'has a reasonable response time': (r) => r.timings.duration <= 1000,
        'time to first byte is short': (r) => r.timings.waiting < 500
    });

    sleep(1);
}

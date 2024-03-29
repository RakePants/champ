export default function hashToNumber(hash) {
    const cleanedHash = hash.replace(/[^a-zA-Z0-9]/g, '');

    // Преобразование хеша в число, используя parseInt с основанием 16 (шестнадцатеричное число)
    const number = parseInt(cleanedHash, 16);

    return number % 10000;
}
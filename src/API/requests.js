import axiosInstance from ".";

async function updateTicketStatus(status, id) {
    const response = await axiosInstance.get(`/tickets/${status}`)
    .catch(err => {
        console.log(err);
    });
    return response.data;
}

export async function getNew() {
    return await updateTicketStatus('get_new');
}


export async function getAccept() {
    return await updateTicketStatus('get_accepted');
}

export async function getComplete() {
    return await updateTicketStatus('get_completed');
}

export async function getCancel() {
    return await updateTicketStatus('get_cancel');
}

export async function getAll() {
    return await updateTicketStatus('get_all');
}

export async function getById() {
    return await updateTicketStatus('get_by_id');
}

export async function getContractor() {
    const response = await axiosInstance.get('/contractors/get_all')
    .catch(err => {
        console.log(err);
    })
    return response.data
}
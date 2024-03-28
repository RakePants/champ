import axiosInstance from ".";

async function updateTicketStatus(status, id) {
    const response = await axiosInstance.put(`/tickets/${status}`, { id })
    .catch(err => {
        console.log(err);
    });

    return response.data;
}

export async function accept(id) {
    return updateTicketStatus('accept', id);
}

export async function decline(id) {
    return updateTicketStatus('decline', id);
}

export async function complete(id) {
    return updateTicketStatus('complete', id);
}
